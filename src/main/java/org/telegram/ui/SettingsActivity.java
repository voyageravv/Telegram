/*
 * This is the source code of Telegram for Android v. 1.3.2.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013.
 */

package org.telegram.ui;

import android.app.Activity;
import android.app.AlertDialog;
<<<<<<< HEAD
import android.app.ProgressDialog;
=======
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
<<<<<<< HEAD
import android.util.Base64;
=======
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.telegram.PhoneFormat.PhoneFormat;
<<<<<<< HEAD
import org.telegram.messenger.SerializedData;
import org.telegram.messenger.TLClassStore;
import org.telegram.messenger.TLObject;
import org.telegram.messenger.TLRPC;
=======
import org.telegram.TL.TLObject;
import org.telegram.TL.TLRPC;
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
import org.telegram.messenger.ConnectionsManager;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.RPCRequest;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.objects.PhotoObject;
import org.telegram.ui.Views.AvatarUpdater;
import org.telegram.ui.Views.BackupImageView;
import org.telegram.ui.Views.BaseFragment;
import org.telegram.ui.Views.OnSwipeTouchListener;

import java.io.File;
import java.util.ArrayList;

public class SettingsActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private ListView listView;
    private ListAdapter listAdapter;
    private AvatarUpdater avatarUpdater = new AvatarUpdater();

    int profileRow;
    int numberSectionRow;
    int numberRow;
    int settingsSectionRow;
    int textSizeRow;
    int enableAnimationsRow;
    int notificationRow;
    int blockedRow;
    int backgroundRow;
    int supportSectionRow;
    int askQuestionRow;
    int logoutRow;
    int sendLogsRow;
<<<<<<< HEAD
    int clearLogsRow;
=======
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
    int rowCount;
    int messagesSectionRow;
    int sendByEnterRow;
    int hideLastSeenRow;
    int terminateSessionsRow;
    int photoDownloadSection;
    int photoDownloadChatRow;
    int photoDownloadPrivateRow;

    @Override
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        avatarUpdater.parentFragment = this;
        avatarUpdater.delegate = new AvatarUpdater.AvatarUpdaterDelegate() {
            @Override
            public void didUploadedPhoto(TLRPC.InputFile file, TLRPC.PhotoSize small, TLRPC.PhotoSize big) {
                TLRPC.TL_photos_uploadProfilePhoto req = new TLRPC.TL_photos_uploadProfilePhoto();
                req.caption = "";
                req.crop = new TLRPC.TL_inputPhotoCropAuto();
                req.file = file;
                req.geo_point = new TLRPC.TL_inputGeoPointEmpty();
                ConnectionsManager.Instance.performRpc(req, new RPCRequest.RPCRequestDelegate() {
                    @Override
                    public void run(TLObject response, TLRPC.TL_error error) {
                        if (error == null) {
                            TLRPC.User user = MessagesController.Instance.users.get(UserConfig.clientUserId);
                            if (user == null) {
                                user = UserConfig.currentUser;
                                if (user == null) {
                                    return;
                                }
                                MessagesController.Instance.users.put(user.id, user);
                            } else {
                                UserConfig.currentUser = user;
                            }
                            if (user == null) {
                                return;
                            }
                            TLRPC.TL_photos_photo photo = (TLRPC.TL_photos_photo)response;
                            ArrayList<TLRPC.PhotoSize> sizes = photo.photo.sizes;
                            TLRPC.PhotoSize smallSize = PhotoObject.getClosestPhotoSizeWithSize(sizes, 100, 100);
                            TLRPC.PhotoSize bigSize = PhotoObject.getClosestPhotoSizeWithSize(sizes, 1000, 1000);
                            user.photo = new TLRPC.TL_userProfilePhoto();
                            user.photo.photo_id = photo.photo.id;
                            if (smallSize != null) {
                                user.photo.photo_small = smallSize.location;
                            }
                            if (bigSize != null) {
                                user.photo.photo_big = bigSize.location;
                            } else if (smallSize != null) {
                                user.photo.photo_small = smallSize.location;
                            }
                            MessagesStorage.Instance.clearUserPhotos(user.id);
                            ArrayList<TLRPC.User> users = new ArrayList<TLRPC.User>();
                            users.add(user);
                            MessagesStorage.Instance.putUsersAndChats(users, null, false, true);
                            Utilities.RunOnUIThread(new Runnable() {
                                @Override
                                public void run() {
                                    NotificationCenter.Instance.postNotificationName(MessagesController.updateInterfaces, MessagesController.UPDATE_MASK_ALL);
                                    UserConfig.saveConfig(true);
                                }
                            });
                        }
                    }
                }, null, true, RPCRequest.RPCRequestClassGeneric);
            }
        };
        NotificationCenter.Instance.addObserver(this, MessagesController.updateInterfaces);


        rowCount = 0;
        profileRow = rowCount++;
        numberSectionRow = rowCount++;
        numberRow = rowCount++;
        settingsSectionRow = rowCount++;
        enableAnimationsRow = rowCount++;
        notificationRow = rowCount++;
        blockedRow = rowCount++;
        backgroundRow = rowCount++;
        terminateSessionsRow = rowCount++;
        photoDownloadSection = rowCount++;
        photoDownloadChatRow = rowCount++;
        photoDownloadPrivateRow = rowCount++;
        messagesSectionRow = rowCount++;
        textSizeRow = rowCount++;
        sendByEnterRow = rowCount++;
        hideLastSeenRow = rowCount++;
        supportSectionRow = rowCount++;
        if (ConnectionsManager.DEBUG_VERSION) {
            sendLogsRow = rowCount++;
<<<<<<< HEAD
            clearLogsRow = rowCount++;
=======
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
        }
        askQuestionRow = rowCount++;
        logoutRow = rowCount++;

        return true;
    }

    @Override
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.Instance.removeObserver(this, MessagesController.updateInterfaces);
        avatarUpdater.clear();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (fragmentView == null) {
            fragmentView = inflater.inflate(R.layout.settings_layout, container, false);
            listAdapter = new ListAdapter(parentActivity);
            listView = (ListView)fragmentView.findViewById(R.id.listView);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == textSizeRow) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(parentActivity);
                        builder.setTitle(getStringEntry(R.string.TextSize));
                        builder.setItems(new CharSequence[]{String.format("%d", 12), String.format("%d", 13), String.format("%d", 14), String.format("%d", 15), String.format("%d", 16), String.format("%d", 17), String.format("%d", 18), String.format("%d", 19), String.format("%d", 20)}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putInt("fons_size", 12 + which);
<<<<<<< HEAD
                                MessagesController.Instance.fontSize = 12 + which;
=======
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                                editor.commit();
                                if (listView != null) {
                                    listView.invalidateViews();
                                }
                            }
                        });
                        builder.setNegativeButton(getStringEntry(R.string.Cancel), null);
                        builder.show().setCanceledOnTouchOutside(true);
                    } else if (i == enableAnimationsRow) {
                        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
                        boolean animations = preferences.getBoolean("view_animations", true);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("view_animations", !animations);
                        editor.commit();
                        if (listView != null) {
                            listView.invalidateViews();
                        }
                    } else if (i == notificationRow) {
<<<<<<< HEAD
                        ((LaunchActivity)parentActivity).presentFragment(new SettingsNotificationsActivity(), "settings_notifications", false);
                    } else if (i == blockedRow) {
                        ((LaunchActivity)parentActivity).presentFragment(new SettingsBlockedUsers(), "settings_blocked", false);
                    } else if (i == backgroundRow) {
                        ((LaunchActivity)parentActivity).presentFragment(new SettingsWallpapersActivity(), "settings_wallpapers", false);
                    } else if (i == askQuestionRow) {
                        final SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
                        int uid = preferences.getInt("support_id", 0);
                        TLRPC.User supportUser = null;
                        if (uid != 0) {
                            supportUser = MessagesController.Instance.users.get(uid);
                            if (supportUser == null) {
                                String userString = preferences.getString("support_user", null);
                                if (userString != null) {
                                    try {
                                        byte[] datacentersBytes = Base64.decode(userString, Base64.DEFAULT);
                                        if (datacentersBytes != null) {
                                            SerializedData data = new SerializedData(datacentersBytes);
                                            supportUser = (TLRPC.User)TLClassStore.Instance().TLdeserialize(data, data.readInt32());

                                        }
                                    } catch (Exception e) {
                                        FileLog.e("tmessages", e);
                                        supportUser = null;
                                    }
                                }
                            }
                        }
                        if (supportUser == null) {
                            if (parentActivity == null) {
                                return;
                            }
                            final ProgressDialog progressDialog = new ProgressDialog(parentActivity);
                            progressDialog.setMessage(parentActivity.getString(R.string.Loading));
                            progressDialog.setCanceledOnTouchOutside(false);
                            progressDialog.setCancelable(false);
                            progressDialog.show();
                            TLRPC.TL_help_getSupport req = new TLRPC.TL_help_getSupport();
                            ConnectionsManager.Instance.performRpc(req, new RPCRequest.RPCRequestDelegate() {
                                @Override
                                public void run(TLObject response, TLRPC.TL_error error) {
                                    if (error == null) {

                                        final TLRPC.TL_help_support res = (TLRPC.TL_help_support)response;
                                        Utilities.RunOnUIThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                SharedPreferences.Editor editor = preferences.edit();
                                                editor.putInt("support_id", res.user.id);
                                                SerializedData data = new SerializedData();
                                                res.user.serializeToStream(data);
                                                editor.putString("support_user", Base64.encodeToString(data.toByteArray(), Base64.DEFAULT));
                                                editor.commit();
                                                try {
                                                    progressDialog.dismiss();
                                                } catch (Exception e) {
                                                    FileLog.e("tmessages", e);
                                                }
                                                MessagesController.Instance.users.put(res.user.id, res.user);
                                                ChatActivity fragment = new ChatActivity();
                                                Bundle bundle = new Bundle();
                                                bundle.putInt("user_id", res.user.id);
                                                fragment.setArguments(bundle);
                                                ((LaunchActivity)parentActivity).presentFragment(fragment, "chat" + Math.random(), false);
                                            }
                                        });
                                    } else {
                                        Utilities.RunOnUIThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    progressDialog.dismiss();
                                                } catch (Exception e) {
                                                    FileLog.e("tmessages", e);
                                                }
                                            }
                                        });
                                    }
                                }
                            }, null, true, RPCRequest.RPCRequestClassGeneric);
                        } else {
                            MessagesController.Instance.users.putIfAbsent(supportUser.id, supportUser);
                            ChatActivity fragment = new ChatActivity();
                            Bundle bundle = new Bundle();
                            bundle.putInt("user_id", supportUser.id);
                            fragment.setArguments(bundle);
                            ((LaunchActivity)parentActivity).presentFragment(fragment, "chat" + Math.random(), false);
                        }
                    } else if (i == sendLogsRow) {
                        sendLogs();
                    } else if (i == clearLogsRow) {
                        FileLog.cleanupLogs();
=======
                        ((ApplicationActivity)parentActivity).presentFragment(new SettingsNotificationsActivity(), "settings_notifications", false);
                    } else if (i == blockedRow) {
                        ((ApplicationActivity)parentActivity).presentFragment(new SettingsBlockedUsers(), "settings_blocked", false);
                    } else if (i == backgroundRow) {
                        ((ApplicationActivity)parentActivity).presentFragment(new SettingsWallpapersActivity(), "settings_wallpapers", false);
                    } else if (i == askQuestionRow) {
                        ChatActivity fragment = new ChatActivity();
                        Bundle bundle = new Bundle();
                        bundle.putInt("user_id", 333000);
                        fragment.setArguments(bundle);
                        ((ApplicationActivity)parentActivity).presentFragment(fragment, "chat" + Math.random(), false);
                    } else if (i == sendLogsRow) {
                        sendLogs();
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                    } else if (i == sendByEnterRow) {
                        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
                        boolean send = preferences.getBoolean("send_by_enter", false);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("send_by_enter", !send);
                        editor.commit();
                        if (listView != null) {
                            listView.invalidateViews();
                        }
                    } else if (i == hideLastSeenRow) {
                        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
                        boolean hide = preferences.getBoolean("hideLastSeen", false);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("hideLastSeen", !hide);
                        editor.commit();
                        if (listView != null) {
                            listView.invalidateViews();
                        }
                    } else if (i == terminateSessionsRow) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(parentActivity);
                        builder.setMessage(getStringEntry(R.string.AreYouSure));
                        builder.setTitle(getStringEntry(R.string.AppName));
                        builder.setPositiveButton(getStringEntry(R.string.OK), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                TLRPC.TL_auth_resetAuthorizations req = new TLRPC.TL_auth_resetAuthorizations();
                                ConnectionsManager.Instance.performRpc(req, new RPCRequest.RPCRequestDelegate() {
                                    @Override
                                    public void run(TLObject response, TLRPC.TL_error error) {
                                        ActionBarActivity inflaterActivity = parentActivity;
                                        if (inflaterActivity == null) {
<<<<<<< HEAD
                                            inflaterActivity = (ActionBarActivity)getActivity();
=======
                                            inflaterActivity = (ActionBarActivity) getActivity();
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                                        }
                                        if (inflaterActivity == null) {
                                            return;
                                        }
                                        if (error == null && response instanceof TLRPC.TL_boolTrue) {
                                            Toast toast = Toast.makeText(inflaterActivity, R.string.TerminateAllSessions, Toast.LENGTH_SHORT);
                                            toast.show();
                                        } else {
                                            Toast toast = Toast.makeText(inflaterActivity, R.string.UnknownError, Toast.LENGTH_SHORT);
                                            toast.show();
                                        }
                                        UserConfig.registeredForPush = false;
                                        MessagesController.Instance.registerForPush(UserConfig.pushString);
                                    }
                                }, null, true, RPCRequest.RPCRequestClassGeneric);
                            }
                        });
                        builder.setNegativeButton(getStringEntry(R.string.Cancel), null);
                        builder.show().setCanceledOnTouchOutside(true);
                    } else if (i == photoDownloadChatRow) {
                        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
                        boolean value = preferences.getBoolean("photo_download_chat", true);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("photo_download_chat", !value);
                        editor.commit();
                        if (listView != null) {
                            listView.invalidateViews();
                        }
                    } else if (i == photoDownloadPrivateRow) {
                        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
                        boolean value = preferences.getBoolean("photo_download_user", true);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("photo_download_user", !value);
                        editor.commit();
                        if (listView != null) {
                            listView.invalidateViews();
                        }
                    }
<<<<<<< HEAD
                else if (i == 6) {
                    UserConfig.saveIncomingPhotos = !UserConfig.saveIncomingPhotos;
                    listView.invalidateViews();
                }
=======
//                else if (i == 6) {
//                    UserConfig.saveIncomingPhotos = !UserConfig.saveIncomingPhotos;
//                    listView.invalidateViews();
//                }
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                }
            });

            listView.setOnTouchListener(new OnSwipeTouchListener() {
                public void onSwipeRight() {
                    finishFragment(true);
                }
            });
        } else {
            ViewGroup parent = (ViewGroup)fragmentView.getParent();
            if (parent != null) {
                parent.removeView(fragmentView);
            }
        }
        return fragmentView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
<<<<<<< HEAD
        avatarUpdater.onActivityResult(requestCode, resultCode, data);
=======
        if (requestCode == 232) {
            FileLog.cleanupLogs();
        } else {
            avatarUpdater.onActivityResult(requestCode, resultCode, data);
        }
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
    }

    @Override
    public void didReceivedNotification(int id, Object... args) {
        if (id == MessagesController.updateInterfaces) {
            int mask = (Integer)args[0];
            if ((mask & MessagesController.UPDATE_MASK_AVATAR) != 0 || (mask & MessagesController.UPDATE_MASK_NAME) != 0) {
                if (listView != null) {
                    listView.invalidateViews();
                }
            }
        }
    }

    @Override
    public void applySelfActionBar() {
        if (parentActivity == null) {
            return;
        }
        ActionBar actionBar = parentActivity.getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowCustomEnabled(false);
        actionBar.setSubtitle(null);
        actionBar.setCustomView(null);
        actionBar.setTitle(getStringEntry(R.string.Settings));

        TextView title = (TextView)parentActivity.findViewById(R.id.action_bar_title);
        if (title == null) {
            final int subtitleId = parentActivity.getResources().getIdentifier("action_bar_title", "id", "android");
            title = (TextView)parentActivity.findViewById(subtitleId);
        }
        if (title != null) {
            title.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            title.setCompoundDrawablePadding(0);
        }
    }

    private void sendLogs() {
        try {
            ArrayList<Uri> uris = new ArrayList<Uri>();
            File sdCard = ApplicationLoader.applicationContext.getExternalFilesDir(null);
            File dir = new File (sdCard.getAbsolutePath() + "/logs");
            File[] files = dir.listFiles();
            for (File file : files) {
                uris.add(Uri.fromFile(file));
            }

            if (uris.isEmpty()) {
                return;
            }
            Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
            i.setType("message/rfc822") ;
<<<<<<< HEAD
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{ConnectionsManager.SEND_LOGS_EMAIL});
            i.putExtra(Intent.EXTRA_SUBJECT, "last logs");
            i.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
            startActivity(Intent.createChooser(i, "Select email application."));
=======
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"voyageravv@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "last logs");
            i.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
            startActivityForResult(Intent.createChooser(i, "Select email application."), 232);
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFinish) {
            return;
        }
        if (getActivity() == null) {
            return;
        }
        if (!firstStart && listAdapter != null) {
            listAdapter.notifyDataSetChanged();
        }
        firstStart = false;
<<<<<<< HEAD
        ((LaunchActivity)parentActivity).showActionBar();
        ((LaunchActivity)parentActivity).updateActionBar();
=======
        ((ApplicationActivity)parentActivity).showActionBar();
        ((ApplicationActivity)parentActivity).updateActionBar();
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finishFragment();
                break;
        }
        return true;
    }

    private class ListAdapter extends BaseAdapter {
        private Context mContext;

        public ListAdapter(Context context) {
            mContext = context;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int i) {
            return i == textSizeRow || i == enableAnimationsRow || i == blockedRow || i == notificationRow || i == backgroundRow ||
<<<<<<< HEAD
                    i == askQuestionRow || i == sendLogsRow || i == sendByEnterRow || i == hideLastSeenRow || i == terminateSessionsRow || i == photoDownloadPrivateRow ||
                    i == photoDownloadChatRow || i == clearLogsRow;
=======
                    i == askQuestionRow || i == sendLogsRow || i == sendByEnterRow ||i == hideLastSeenRow || i == terminateSessionsRow || i == photoDownloadPrivateRow ||
                    i == photoDownloadChatRow;
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
        }

        @Override
        public int getCount() {
            return rowCount;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            int type = getItemViewType(i);
            if (type == 0) {
                if (view == null) {
                    LayoutInflater li = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = li.inflate(R.layout.settings_name_layout, viewGroup, false);

                    ImageButton button = (ImageButton)view.findViewById(R.id.settings_edit_name);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
<<<<<<< HEAD
                            ((LaunchActivity)parentActivity).presentFragment(new SettingsChangeNameActivity(), "change_name", false);
=======
                            ((ApplicationActivity)parentActivity).presentFragment(new SettingsChangeNameActivity(), "change_name", false);
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                        }
                    });

                    final ImageButton button2 = (ImageButton)view.findViewById(R.id.settings_change_avatar_button);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(parentActivity);

                            CharSequence[] items;

                            TLRPC.User user = MessagesController.Instance.users.get(UserConfig.clientUserId);
                            if (user == null) {
                                user = UserConfig.currentUser;
                            }
                            if (user == null) {
                                return;
                            }
                            boolean fullMenu = false;
                            if (user.photo != null && user.photo.photo_big != null && !(user.photo instanceof TLRPC.TL_userProfilePhotoEmpty)) {
                                items = new CharSequence[] {getStringEntry(R.string.OpenPhoto), getStringEntry(R.string.FromCamera), getStringEntry(R.string.FromGalley), getStringEntry(R.string.DeletePhoto)};
                                fullMenu = true;
                            } else {
                                items = new CharSequence[] {getStringEntry(R.string.FromCamera), getStringEntry(R.string.FromGalley)};
                            }

                            final boolean full = fullMenu;
                            builder.setItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (i == 0 && full) {
                                        TLRPC.User user = MessagesController.Instance.users.get(UserConfig.clientUserId);
                                        if (user != null && user.photo != null && user.photo.photo_big != null) {
                                            NotificationCenter.Instance.addToMemCache(56, user.id);
                                            NotificationCenter.Instance.addToMemCache(53, user.photo.photo_big);
                                            Intent intent = new Intent(parentActivity, GalleryImageViewer.class);
                                            startActivity(intent);
                                        }
                                    } else if (i == 0 && !full || i == 1 && full) {
                                        avatarUpdater.openCamera();
                                    } else if (i == 1 && !full || i == 2 && full) {
                                        avatarUpdater.openGallery();
                                    } else if (i == 3) {
                                        TLRPC.TL_photos_updateProfilePhoto req = new TLRPC.TL_photos_updateProfilePhoto();
                                        req.id = new TLRPC.TL_inputPhotoEmpty();
                                        req.crop = new TLRPC.TL_inputPhotoCropAuto();
                                        UserConfig.currentUser.photo = new TLRPC.TL_userProfilePhotoEmpty();
                                        TLRPC.User user = MessagesController.Instance.users.get(UserConfig.clientUserId);
                                        if (user == null) {
                                            user = UserConfig.currentUser;
                                        }
                                        if (user == null) {
                                            return;
                                        }
                                        if (user != null) {
                                            user.photo = UserConfig.currentUser.photo;
                                        }
                                        NotificationCenter.Instance.postNotificationName(MessagesController.updateInterfaces, MessagesController.UPDATE_MASK_ALL);
                                        ConnectionsManager.Instance.performRpc(req, new RPCRequest.RPCRequestDelegate() {
                                            @Override
                                            public void run(TLObject response, TLRPC.TL_error error) {
                                                if (error == null) {
                                                    TLRPC.User user = MessagesController.Instance.users.get(UserConfig.clientUserId);
                                                    if (user == null) {
                                                        user = UserConfig.currentUser;
                                                        MessagesController.Instance.users.put(user.id, user);
                                                    } else {
                                                        UserConfig.currentUser = user;
                                                    }
                                                    if (user == null) {
                                                        return;
                                                    }
                                                    MessagesStorage.Instance.clearUserPhotos(user.id);
                                                    ArrayList<TLRPC.User> users = new ArrayList<TLRPC.User>();
                                                    users.add(user);
                                                    MessagesStorage.Instance.putUsersAndChats(users, null, false, true);
                                                    user.photo = (TLRPC.UserProfilePhoto)response;
                                                    Utilities.RunOnUIThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            NotificationCenter.Instance.postNotificationName(MessagesController.updateInterfaces, MessagesController.UPDATE_MASK_ALL);
                                                            UserConfig.saveConfig(true);
                                                        }
                                                    });
                                                }
                                            }
                                        }, null, true, RPCRequest.RPCRequestClassGeneric);
                                    }
                                }
                            });
                            builder.show().setCanceledOnTouchOutside(true);
                        }
                    });
                }
                TextView textView = (TextView)view.findViewById(R.id.settings_name);
                Typeface typeface = Utilities.getTypeface("fonts/rmedium.ttf");
                textView.setTypeface(typeface);
                TLRPC.User user = MessagesController.Instance.users.get(UserConfig.clientUserId);
                if (user == null) {
                    user = UserConfig.currentUser;
                }
                if (user != null) {
                    textView.setText(Utilities.formatName(user.first_name, user.last_name));
                    BackupImageView avatarImage = (BackupImageView)view.findViewById(R.id.settings_avatar_image);
                    TLRPC.FileLocation photo = null;
                    if (user.photo != null) {
                        photo = user.photo.photo_small;
                    }
                    avatarImage.setImage(photo, null, Utilities.getUserAvatarForId(user.id));
                }
                return view;
            } else if (type == 1) {
                if (view == null) {
                    LayoutInflater li = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = li.inflate(R.layout.settings_section_layout, viewGroup, false);
                }
                TextView textView = (TextView)view.findViewById(R.id.settings_section_text);
                if (i == numberSectionRow) {
                    textView.setText(getStringEntry(R.string.YourPhoneNumber));
                } else if (i == settingsSectionRow) {
                    textView.setText(getStringEntry(R.string.SETTINGS));
                } else if (i == supportSectionRow) {
                    textView.setText(getStringEntry(R.string.Support));
                } else if (i == messagesSectionRow) {
                    textView.setText(getStringEntry(R.string.MessagesSettings));
                } else if (i == photoDownloadSection) {
                    textView.setText(getStringEntry(R.string.AutomaticPhotoDownload));
                }
            } else if (type == 2) {
                if (view == null) {
                    LayoutInflater li = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = li.inflate(R.layout.settings_row_button_layout, viewGroup, false);
                }
                TextView textView = (TextView)view.findViewById(R.id.settings_row_text);
                View divider = view.findViewById(R.id.settings_row_divider);
                if (i == numberRow) {
                    TLRPC.User user = UserConfig.currentUser;
                    if (user != null && user.phone != null && user.phone.length() != 0) {
                        textView.setText(PhoneFormat.Instance.format("+" + user.phone));
                    } else {
                        textView.setText("Unknown");
                    }
                    divider.setVisibility(View.INVISIBLE);
                } else if (i == notificationRow) {
                    textView.setText(getStringEntry(R.string.NotificationsAndSounds));
                    divider.setVisibility(View.VISIBLE);
                } else if (i == blockedRow) {
                    textView.setText(getStringEntry(R.string.BlockedUsers));
                    divider.setVisibility(backgroundRow != 0 ? View.VISIBLE : View.INVISIBLE);
                } else if (i == backgroundRow) {
                    textView.setText(getStringEntry(R.string.ChatBackground));
                    divider.setVisibility(View.VISIBLE);
                } else if (i == sendLogsRow) {
                    textView.setText("Send Logs");
                    divider.setVisibility(View.VISIBLE);
<<<<<<< HEAD
                } else if (i == clearLogsRow) {
                    textView.setText("Clear Logs");
                    divider.setVisibility(View.VISIBLE);
=======
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                } else if (i == askQuestionRow) {
                    textView.setText(getStringEntry(R.string.AskAQuestion));
                    divider.setVisibility(View.INVISIBLE);
                } else if (i == terminateSessionsRow) {
                    textView.setText(getStringEntry(R.string.TerminateAllSessions));
                    divider.setVisibility(View.INVISIBLE);
                }
            } else if (type == 3) {
                if (view == null) {
                    LayoutInflater li = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = li.inflate(R.layout.settings_row_check_layout, viewGroup, false);
                }
                TextView textView = (TextView)view.findViewById(R.id.settings_row_text);
                View divider = view.findViewById(R.id.settings_row_divider);
                ImageView checkButton = (ImageView)view.findViewById(R.id.settings_row_check_button);
                SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
                if (i == enableAnimationsRow) {
                    textView.setText(getStringEntry(R.string.EnableAnimations));
                    divider.setVisibility(View.VISIBLE);
                    boolean enabled = preferences.getBoolean("view_animations", true);
                    if (enabled) {
                        checkButton.setImageResource(R.drawable.btn_check_on);
                    } else {
                        checkButton.setImageResource(R.drawable.btn_check_off);
                    }
                } else if (i == sendByEnterRow) {
                    textView.setText(getStringEntry(R.string.SendByEnter));
                    divider.setVisibility(View.INVISIBLE);
                    boolean enabled = preferences.getBoolean("send_by_enter", false);
                    if (enabled) {
                        checkButton.setImageResource(R.drawable.btn_check_on);
                    } else {
                        checkButton.setImageResource(R.drawable.btn_check_off);
<<<<<<< HEAD
                    }
=======
                    } 
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                } else if (i == hideLastSeenRow) {
                    textView.setText(getStringEntry(R.string.HideLastSeen));
                    divider.setVisibility(View.INVISIBLE);
                    boolean enabled = preferences.getBoolean("hideLastSeen", false);
                    if (enabled) {
                        checkButton.setImageResource(R.drawable.btn_check_on);
                    } else {
                        checkButton.setImageResource(R.drawable.btn_check_off);
                    }
<<<<<<< HEAD
                } else if (i == photoDownloadChatRow) {
=======

                }
                else if (i == photoDownloadChatRow) {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                    textView.setText(getStringEntry(R.string.AutomaticPhotoDownloadGroups));
                    divider.setVisibility(View.VISIBLE);
                    boolean enabled = preferences.getBoolean("photo_download_chat", true);
                    if (enabled) {
                        checkButton.setImageResource(R.drawable.btn_check_on);
                    } else {
                        checkButton.setImageResource(R.drawable.btn_check_off);
                    }
<<<<<<< HEAD
                } else if (i == photoDownloadPrivateRow) {
=======
                }
                else if (i == photoDownloadPrivateRow) {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                    textView.setText(getStringEntry(R.string.AutomaticPhotoDownloadPrivateChats));
                    divider.setVisibility(View.INVISIBLE);
                    boolean enabled = preferences.getBoolean("photo_download_user", true);
                    if (enabled) {
                        checkButton.setImageResource(R.drawable.btn_check_on);
                    } else {
                        checkButton.setImageResource(R.drawable.btn_check_off);
                    }
                }
<<<<<<< HEAD
                if (i == 7) {
                    textView.setText(getStringEntry(R.string.SaveIncomingPhotos));
                    divider.setVisibility(View.INVISIBLE);

                    checkButton = (ImageView)view.findViewById(R.id.settings_row_check_button);
                    if (UserConfig.saveIncomingPhotos) {
                        checkButton.setImageResource(R.drawable.btn_check_on);
                    } else {
                        checkButton.setImageResource(R.drawable.btn_check_off);
                   }
                }
=======
//                if (i == 7) {
//                    textView.setText(getStringEntry(R.string.SaveIncomingPhotos));
//                    divider.setVisibility(View.INVISIBLE);
//
//                    ImageView checkButton = (ImageView)view.findViewById(R.id.settings_row_check_button);
//                    if (UserConfig.saveIncomingPhotos) {
//                        checkButton.setImageResource(R.drawable.btn_check_on);
//                    } else {
//                        checkButton.setImageResource(R.drawable.btn_check_off);
//                    }
//                }
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
            } else if (type == 4) {
                if (view == null) {
                    LayoutInflater li = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = li.inflate(R.layout.settings_logout_button, viewGroup, false);
                    TextView textView = (TextView)view.findViewById(R.id.settings_row_text);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(parentActivity);
                            builder.setMessage(getStringEntry(R.string.AreYouSure));
                            builder.setTitle(getStringEntry(R.string.AppName));
                            builder.setPositiveButton(getStringEntry(R.string.OK), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    NotificationCenter.Instance.postNotificationName(1234);
                                    MessagesController.Instance.unregistedPush();
                                    listView.setAdapter(null);
                                    UserConfig.clearConfig();
                                }
                            });
                            builder.setNegativeButton(getStringEntry(R.string.Cancel), null);
                            builder.show().setCanceledOnTouchOutside(true);
                        }
                    });
                }
            } else if (type == 5) {
                if (view == null) {
                    LayoutInflater li = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = li.inflate(R.layout.user_profile_leftright_row_layout, viewGroup, false);
                }
                TextView textView = (TextView)view.findViewById(R.id.settings_row_text);
                TextView detailTextView = (TextView)view.findViewById(R.id.settings_row_text_detail);
                View divider = view.findViewById(R.id.settings_row_divider);
                if (i == textSizeRow) {
                    SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", Activity.MODE_PRIVATE);
                    int size = preferences.getInt("fons_size", 16);
                    detailTextView.setText(String.format("%d", size));
                    textView.setText(ApplicationLoader.applicationContext.getString(R.string.TextSize));
                    divider.setVisibility(View.VISIBLE);
                }
            }

            return view;
        }

        @Override
        public int getItemViewType(int i) {
            if (i == profileRow) {
                return 0;
            } else if (i == numberSectionRow || i == settingsSectionRow || i == supportSectionRow || i == messagesSectionRow || i == photoDownloadSection) {
                return 1;
            } else if (i == textSizeRow) {
                return 5;
            } else if (i == enableAnimationsRow || i == sendByEnterRow || i == hideLastSeenRow || i == photoDownloadChatRow || i == photoDownloadPrivateRow) {
                return 3;
<<<<<<< HEAD
            } else if (i == numberRow || i == notificationRow || i == blockedRow || i == backgroundRow || i == askQuestionRow || i == sendLogsRow || i == terminateSessionsRow || i == clearLogsRow) {
=======
            } else if (i == numberRow || i == notificationRow || i == blockedRow || i == backgroundRow || i == askQuestionRow || i == sendLogsRow || i == terminateSessionsRow) {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                return 2;
            } else if (i == logoutRow) {
                return 4;
            } else {
                return 2;
            }
        }

        @Override
        public int getViewTypeCount() {
            return 6;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }
}
